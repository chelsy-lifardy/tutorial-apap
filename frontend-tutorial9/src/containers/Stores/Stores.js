import React, { Component } from "react";

import Store from "../../components/Store/Store";
import classes from "./Stores.module.css";
import axios from "../../axios-store.js";
import Modal from "../../components/UI/Modal/Modal";
import Button from "../../components/UI/Button/Button";
import SearchBar from "../../components/UI/SearchBar/SearchBar";
import Pagination from "../../components/UI/Pagination/Pagination";

class Stores extends Component {
  constructor(props) {
    super(props);
    this.state = {
      stores: [],
      searchedStores: [],
      isCreate: false,
      isEdit: false,
      isLoading: true,
      id: "",
      nama: "",
      keterangan: "",
      followers: "",
      totalPage: "",
      currentPage: 1
    };
  }

  componentDidMount() {
    this.loadStores();
  }

  loadStores = async () => {
    const fetchedStores = [];
    const response = await axios.get("/stores");
    for (let key in response.data) {
      fetchedStores.push({
        ...response.data[key]
      });
    }
    this.setState({
      stores: fetchedStores,
      searchedStores: fetchedStores.slice(0, 5),
      totalPage: Math.ceil(fetchedStores.length / 5),
      currentPage: 1
    });
  };

  addStoreHandler = () => {
    this.setState({ isCreate: true });
  };

  canceledHandler = () => {
    this.setState({ isCreate: false, isEdit: false });
  };

  changeHandler = event => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  editStoreHandler(store) {
    this.setState({
      isEdit: true,
      id: store.id,
      nama: store.nama,
      keterangan: store.keterangan,
      followers: store.followers
    });
  }

  async addStore() {
    const storeToAdd = {
      nama: this.state.nama,
      followers: this.state.followers,
      keterangan: this.state.keterangan
    };

    await axios.post("/store", storeToAdd);
    await this.loadStores();
  }

  submitAddStoreHandler = event => {
    event.preventDefault();
    this.setState({ isLoading: true });
    this.addStore();
    this.canceledHandler();

    this.setState({
      nama: "",
      keterangan: "",
      followers: ""
    });
  };

  async editStore() {
    const storeToEdit = {
      id: this.state.id,
      nama: this.state.nama,
      followers: this.state.followers,
      keterangan: this.state.keterangan
    };
    await axios.put("/store/" + this.state.id, storeToEdit);
    await this.loadStores();
  }

  submitEditStoreHandler = event => {
    event.preventDefault();
    this.setState({ isLoading: true });
    this.editStore();
    this.canceledHandler();
  };

  async deleteStoreHandler(storeId) {
    await axios.delete(`/store/${storeId}`);
    await this.loadStores();
  }

  search = text => {
    if (text === "") {
      this.setState({ searchedStores: this.state.stores });
    } else {
      const filteredStores = this.state.stores.filter(store => {
        return store.nama.toLowerCase().indexOf(text.toLowerCase()) !== -1;
      });

      this.setState({ searchedStores: filteredStores });
    }
  };

  changePage = pageNum => {
    const selectedPage = this.state.stores.slice(
      pageNum !== 1 ? pageNum * 5 - 5 : 0,
      pageNum !== 1 ? pageNum * 5 : 5
    );

    this.setState({ searchedStores: selectedPage, currentPage: pageNum });
  };

  renderForm() {
    const { isEdit } = this.state;
    return (
      <form>
        <input
          className={classes.Input}
          name="nama"
          type="text"
          placeholder="Name"
          value={this.state.nama}
          onChange={this.changeHandler}
        />

        <input
          className={classes.Input}
          name="followers"
          type="number"
          placeholder="Followers"
          value={this.state.followers}
          onChange={this.changeHandler}
        />

        <input
          className={classes.TextArea}
          name="keterangan"
          type="text"
          placeholder="Description"
          value={this.state.keterangan}
          onChange={this.changeHandler}
        />
        <Button btnType="Danger" onClick={this.canceledHandler}>
          CANCEL
        </Button>
        <Button
          btnType="Success"
          onClick={
            isEdit ? this.submitEditStoreHandler : this.submitAddStoreHandler
          }
        >
          SUBMIT
        </Button>
      </form>
    );
  }

  render() {
    return (
      <React.Fragment>
        <Modal
          show={this.state.isCreate || this.state.isEdit}
          modalClosed={this.canceledHandler}
        >
          {this.renderForm()}
        </Modal>
        <SearchBar onChange={this.search}>Search here</SearchBar>
        <div className={classes.Title}>All Stores</div>
        <div className={classes.ButtonLayout}>
          <button
            className={classes.AddStoreButton}
            onClick={this.addStoreHandler}
          >
            + Add New Store
          </button>
        </div>
        <div className={classes.Stores}>
          {this.state.searchedStores &&
            this.state.searchedStores.map(store => (
              <Store
                key={store.id}
                nama={store.nama}
                followers={store.followers}
                keterangan={store.keterangan}
                edit={() => this.editStoreHandler(store)}
                delete={() => this.deleteStoreHandler(store.id)}
              />
            ))}
        </div>
        <Pagination
          length={this.state.totalPage}
          changePage={this.changePage}
          active={this.state.currentPage}
        ></Pagination>
      </React.Fragment>
    );
  }
}

export default Stores;
