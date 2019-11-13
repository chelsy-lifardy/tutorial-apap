import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import AppFunc from "./AppFunc";
import AppClass from "./App";
import * as serviceWorker from "./serviceWorker";
ReactDOM.render(
  <>
    <div className="mt-5" />
    <AppClass />
    <div className="mt-5 mb-5" />
    <AppFunc />
    <div className="mt-5 mb-5" />
  </>,
  document.getElementById("root")
);
serviceWorker.unregister();
