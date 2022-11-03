import { react, useEffect, useState  } from "react";
import ResultItem from "./ResultItem";
import SearchProvider from "../../../store/SearchProvider";
import classes from "./SearchResult.module.css";
import double_room from "../../../assets/image/double_room.jpg";
import twin_room from "../../../assets/image/twin_room.jpg";
import suite_room from "../../../assets/image/suite_room.jpg";
import family_room from "../../../assets/image/family_room.jpg";

import bathtub from "../../../assets/image/detail_slide/double_twin/bathtub.jpg";
import premium_bed from "../../../assets/image/detail_slide/double_twin/premium_bed.jpg";

import baby_bed from "../../../assets/image/detail_slide/family/baby_bed.jpg";
import kids_pool from "../../../assets/image/detail_slide/family/kids_pool.jpg";

import mini_pool from "../../../assets/image/detail_slide/suite/mini_pool.jpg";
import room_service from "../../../assets/image/detail_slide/suite/room_service.jpg";

const SearchResult = ({ location }) => {
  const [resultArr, setResultArr] = useState([]);
  const searchData = location.state.searchData;

  const findSuitableRoom = (searchInfo) => {
    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(searchInfo),
    };

    try {
      fetch("/searchRoom", requestOptions)
        .then((response) => (response.status === 200 ? response.json() : null))
        .then((result) => {
          setResultArr(result.suitableRooms);
        });
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    findSuitableRoom(searchData);
  }, [searchData]);

  const imgSrcSelector = (roomType) => {
    switch (roomType) {
      case "DOUBLE":
        return double_room;

      case "TWIN":
        return twin_room;

      case "SUITE":
        return suite_room;

      case "FAMILY":
        return family_room;

      default:
        console.log("error");
    }
  };

  const slideSourceSelector = (roomType) => {
 switch (roomType) {
   case "DOUBLE":
   case "TWIN":
     return [bathtub, premium_bed];

   case "SUITE":
     return [mini_pool, room_service];

   case "FAMILY":
     return [baby_bed, kids_pool];

   default:
     console.log("error");
 }
  };

  const resultList = resultArr.map((item) => (
    // <li key={item.id}>{item.roomType}</li>

    <ResultItem

      key={item.id}
      roomId={item.id}
      roomType={item.roomType}
      viewType={item.viewType}
      capacity={item.capacity}
      price={item.price}
      imgSource={imgSrcSelector(item.roomType)}
      slideSource={slideSourceSelector(item.roomType)}
      searchData={searchData}
    />
  ));

  return (
    <SearchProvider>
      <div className={classes.result_container}>
        {
          resultList.length === 0 ? "NO RESULTS FOUND" : resultList
          
        }
      </div>
    </SearchProvider>
  );
};

export default SearchResult;
