import { useEffect, useState } from "react";
import classes from "./MyPage.module.css";
import ProfileBox from "./ProfileBox";

const MyPage = () => {

  const [mypageInfo,setMypageInfo] = useState(null);
  useEffect(() => {
    const authState = sessionStorage.getItem("authState");

    if (authState !== null) {
      mypageHandler();
    }
  }, []);

  
  async function mypageHandler() {
    const authState = JSON.parse(sessionStorage.getItem("authState"));

    if (authState !== null) {
      const requestOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "authorization": authState.authorization,
          "refreshtoken": authState.refreshtoken,
        },
        body: JSON.stringify(authState)
      };

      try {
        fetch("/user/myPage", requestOptions)
          .then((response) =>
            response.status === 200 ? response.json() : null
          )
          .then((result) => {            
            setMypageInfo(result);
          });
      } catch (error) {
        console.log(error);
      }
    }
  }

  return (mypageInfo !== null && <div id="mypageContainer">

    <ProfileBox mypageInfo={mypageInfo}></ProfileBox>


  </div>);
};

export default MyPage;
