import React, { Component } from "react";

export default class Pagination extends Component {
  generatePagination = () => {
    const pages = [];
    for (let i = 1; i <= this.props.length; i++) {
      pages.push(
        <li className="page-item" key={i}>
          <span
            className={`page-link ${this.props.active === i ? "active" : ""}`}
            value={i}
            onClick={() => this.props.changePage(i)}
          >
            {i}
          </span>
        </li>
      );
    }
    return pages;
  };

  render() {
    return (
      <div className="d-flex justify-content-center align-items-center">
        <ul className="pagination">{this.generatePagination()}</ul>
      </div>
    );
  }
}
