import React from 'react';
import DaumPostcode from "react-daum-postcode";

const Postcode = (props) => {
  const handleComplete = (data) => {
    let fullAddress = data.address;
    let extraAddress = "";

    if (data.addressType === "R") {
      if (data.bname !== "") {
        extraAddress += data.bname;
      }
      if (data.buildingName !== "") {
        extraAddress +=
          extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
    }

    props.onChangeZipcode(data.zonecode);
    props.onChangeCity(data.sido + " " +data.sigungu +" "+ data.bname);
    props.onChangeStreet(data.roadAddress);


    console.log(fullAddress); // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'

    props.onClosePopup();
  };

  return <DaumPostcode onComplete={handleComplete} className="post-code" />;
};

export default Postcode;