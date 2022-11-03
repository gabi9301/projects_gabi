import React from "react";
import { Switch, Route } from 'react-router-dom';

import Home from './Home/Home';
import Join from './Join/Join';
import Login from "./Login/Login";
import MyPage from "./MyPage/MyPage";
import SearchResult from "./Home/Search/SearchResult";
import ReservationCheck from "./ReservationInfo/ReservationCheck";
import ReservationResult from './ReservationInfo/ReservationResult'

const MainRoute = () => {
    return (
      <Switch>
        <Route exact path="/" component={Home}></Route>
        <Route exact path="/join" component={Join}></Route>
        <Route exact path="/login" component={Login}></Route>
        <Route exact path="/mypage" component={MyPage}></Route>
        <Route exact path="/logout" component={Login}></Route>
        <Route exact path="/searchResult" component={SearchResult}></Route>
        <Route exact path="/reservationCheck" component={ReservationCheck}></Route>
        <Route exact path="/reservationResult" component={ReservationResult}></Route>
      </Switch>
    );
}

export default MainRoute;