import React, { Component } from "react";

import Layout from "./components/Layout/Layout";
import Stores from "./containers/Stores/Stores";

class App extends Component {
  render() {
    return (
      <div>
        <Layout>
          <Stores />
        </Layout>
      </div>
    );
  }
}

export default App;
