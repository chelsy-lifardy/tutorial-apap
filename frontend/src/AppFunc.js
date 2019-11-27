import React, { useState } from "react";
import List from "components/List";

import dummyItems from "items.json";
/**
 * Building React component using functional programming paradigm
 */
function App() {
  const [favItems, setFavItems] = useState(() => []);
  function handleItemClick(item) {
    const newItems = [...favItems];
    const newItem = { ...item };
    const targetInd = newItems.findIndex(it => it.id === newItem.id);

    if (targetInd < 0) newItems.push(newItem);
    else newItems.splice(targetInd, 1);
    setFavItems(newItems);
  }

  return (
    <div className="container-fluid">
      <h1 className="text-center">
        Welcome!
        <small>Functional</small>
      </h1>
      <div className="container pt-3">
        <div className="row">
          <div className="col-sm">
            <List
              title="Our Menu"
              items={dummyItems}
              onItemClick={handleItemClick}
            />
          </div>
          <div className="col-sm">
            <List
              title="My Favorite"
              items={favItems}
              onItemClick={handleItemClick}
            />
          </div>
        </div>
      </div>
    </div>
  );
}
export default App;
