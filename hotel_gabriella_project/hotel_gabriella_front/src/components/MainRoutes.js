import React from "react";
import { Switch, Route } from 'react-router-dom';

import Home from './Home/Home';
import Join from './Join/Join';
import Login from "./Login/Login";
import MyPage from "./MyPage/MyPage";
import SearchResult from "./Home/Search/SearchResult";

const MainRoute = () => {
    return (
      <Switch>
        <Route exact path="/" component={Home}></Route>
        <Route exact path="/join" component={Join}></Route>
        <Route exact path="/login" component={Login}></Route>
        <Route exact path="/mypage" component={MyPage}></Route>
        <Route exact path="/logout" component={Login}></Route>
        <Route exact path="/searchResult" component={SearchResult}></Route>
      </Switch>
    );
}

export default MainRoute;