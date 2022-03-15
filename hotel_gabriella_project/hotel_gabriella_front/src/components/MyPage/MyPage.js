import { useEffect } from "react";
import classes from "./MyPage.module.css";

const MyPage = () => {
  useEffect(() => {
    const authState = sessionStorage.getItem("authState");

    if (authState !== null) {
      mypageHandler();
    }
  }, []);

  const mypageInfo = {
    account: "",
  };
  async function mypageHandler() {
    const authState = JSON.parse(sessionStorage.getItem("authState"));

    console.log(authState.authorization);

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
            console.log(result);
          });
        //.then((result) =>  console.log(result.account));
      } catch (error) {
        console.log(error);
      }
    }
  }

  return <div id="mypageTest">{mypageInfo.account}</div>;
};

export default MyPage;
