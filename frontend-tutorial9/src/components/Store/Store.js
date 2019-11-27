import React from "react";

import classes from "./Store.module.css";

const Store = props => {
  return (
    <div className={classes.Store}>
      <h3>{props.nama}</h3>
      <p>Followers: {props.followers}</p>
      <p>Keterangan: {props.keterangan}</p>
      <div>
        <button className={classes.EditStoreButton} onClick={props.edit}>
          Edit
        </button>
        <button className={classes.DeleteStoreButton} onClick={props.delete}>
          Delete
        </button>
      </div>
    </div>
  );
};

export default Store;
