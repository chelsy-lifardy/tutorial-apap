import React from "react";

export default function Item(props) {
  const { item, onChange } = props;
  const { nama, deskripsi, harga, checked } = item;

  const handleChange = () =>
    !!onChange && onChange({ ...item, checked: !checked });

  return (
    <button
      type="button"
      className="list-group-item list-group-item-action flex-column align-items-start"
      onClick={handleChange}
    >
      <div className="d-flex w-100 justify-content-between align-items-center">
        <input
          type={checked ? "checkbox" : "hidden"}
          checked={checked}
          onChange={handleChange}
        />
        <h5 className="mb-1">{nama}</h5>
        <small>Rp{harga}</small>
      </div>
      <p className="mb-1">{deskripsi}</p>
    </button>
  );
}
