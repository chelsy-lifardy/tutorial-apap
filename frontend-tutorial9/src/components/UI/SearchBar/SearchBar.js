import React, { Component } from "react";
import "./SearchBar.css";

export default class SearchBar extends Component {
  handleChange = e => {
    this.props.onChange(e.target.value);
  };

  render() {
    const { query } = this.props;
    return (
      <React.Fragment>
        <div className="container h-100">
          <div className="d-flex justify-content-center h-100">
            <div className="searchbar">
              <input
                className="search_input"
                name="search"
                type="text"
                placeholder="Search stores"
                value={query}
                onChange={this.handleChange}
              />
              <span className="search_icon">
                <i className="fas fa-search"></i>
              </span>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}
