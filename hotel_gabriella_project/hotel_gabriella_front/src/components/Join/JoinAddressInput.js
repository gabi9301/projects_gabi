import classes from "./JoinAddressInput.module.css";
import { useState, useEffect } from "react";
import Postcode from "./PostCode";
import PopupDom from "../UI/PopupDom";

const JoinAddressInput = (props) => {
  const [popupShown, setPopupShown] = useState(false);

    // const zipcodeChangeHandler = (zipcode) => {
    //   props.setZipcode(zipcode);
    // };

    // const cityChangeHandler = (city) => {
    //   props.setCity(city);
    // };

    // const streetChangeHandler = (street) => {
    //   props.setStreet(street);
    // };

  const showPopupHandler = () => {
    setPopupShown(true);
  };

  const hidePopupHandler = () => {
    setPopupShown(false);
  };


  const addressSearchHandler = (event) => {
    event.preventDefault();
    showPopupHandler();
  };


  

  return (
    <div className={classes.join_address_container}>
      <div className={classes.address_label}>
        <span>Address</span>
      </div>
      <div className={classes.join_address_inner_container}>
        <div className={classes.join_address_find_wrap}>
          <input
            type="text"
            id="zipcode"
            name="address[zipcode]"
            readOnly
            className={classes.zipcode}
            onChange={props.onChangeZipcode}
            value={props.zipcode}
          />
          <button
            className={classes.find_address}
            onClick={addressSearchHandler}
          >
            도로명 주소 찾기
          </button>
          {popupShown && (
            <PopupDom onClose={hidePopupHandler}>
              <Postcode
                onChangeZipcode={props.onChangeZipcode}
                onChangeCity={props.onChangeCity}
                onChangeStreet={props.onChangeStreet}
                onClosePopup={hidePopupHandler}
              />
            </PopupDom>
          )}
        </div>
        <div className={classes.address_city_street_container}>
          <input
            type="text"
            id="city"
            name="address[city]"
            readOnly
            className={classes.city}
            onChange={props.onChangeCity}
            value={props.city}
          />
          <input
            type="text"
            id="street"
            name="address[street]"
            readOnly
            className={classes.street}
            onChange={props.onChangeStreet}
            value={props.street}
          />
        </div>
        <input
          type="text"
          id="address_detail"
          name="address[address_detail]"
          className={classes.address_detail}
          onChange={props.onChangeAddressDetail}
          value={props.addressDetail}
        />
      </div>
    </div>
  );
};

export default JoinAddressInput;
