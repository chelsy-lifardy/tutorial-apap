import React from "react";

import List from "components/List";

import dummyItems from "items.json";

import "App.css";

export default class App extends React.Component {
  // for class based component, you need to provide render
  // function

  state = {
    favItems: [],
    textDisplay: false
  };

  handleFavClick = item => {
    const newItems = [...this.state.favItems];
    const newItem = { ...item };

    const targetInd = newItems.findIndex(it => it.id === newItem.id);
    if (targetInd < 0) newItems.push(newItem);
    else newItems.splice(targetInd, 1);

    this.setState({ favItems: newItems });
  };

  handleItemClick = item => {
    const newItems = [...this.state.favItems];
    const newItem = { ...item };

    const targetInd = newItems.findIndex(it => it.id === newItem.id);
    if (targetInd < 0) newItems.push(newItem);

    this.setState({ favItems: newItems });
  };

  toggleButton = () => {
    this.setState(currentState => ({
      textDisplay: !currentState.textDisplay
    }));
  };

  render() {
    const { favItems, textDisplay } = this.state;
    return (
      <div className="container-fluid">
        <h1 className="text-center">
          Welcome!
          <small>Class-based</small>
        </h1>
        <div className="d-flex justify-content-center align-items-center">
          {/* <h4 className="bas-margin">Normal</h4> */}
          <label className="switch">
            <input type="checkbox" onClick={this.toggleButton} />
            <span className="slider round"></span>
          </label>
          <h4 className="fav-margin">My Favorites</h4>
        </div>
        <div className="container pt-3">
          <div className="row">
            <div className="col-sm">
              <List
                title="Our Menu"
                items={dummyItems}
                onItemClick={this.handleItemClick}
              />
            </div>
            <div className={`col-sm ${textDisplay ? "d-block" : "d-none"}`}>
              <List
                title="My Favourite"
                items={favItems}
                onItemClick={this.handleFavClick}
              />
            </div>
          </div>
        </div>
      </div>
    );
  }
}
