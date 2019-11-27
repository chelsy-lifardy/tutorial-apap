import React from "react";

import Item from "components/Item";
import EmptyState from "../EmptyState";

export default function List({ title, items, onItemClick }) {
  return (
    <>
      <h3 style={styles.heading}>{title}</h3>
      {items.length !== 0 ? (
        <div className="list-group">
          {items.map(item => (
            <Item key={item.id} item={item} onChange={onItemClick} />
          ))}
        </div>
      ) : (
        <EmptyState />
      )}
    </>
  );
}

const styles = {
  heading: {
    fontFamily: "courier new"
  }
};
