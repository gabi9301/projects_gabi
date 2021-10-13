import React from "react";
import { Switch, Route } from 'react-router-dom';

import Home from './Home/Home';
import Join from './Join/Join';
import Login from "./Login/Login";

const MainRoute = () => {
    return (
      <Switch>
        <Route exact path='/' component={Home}></Route>
        <Route exact path='/join' component={Join}></Route>
        <Route exact path='/login' component={Login}></Route>
      </Switch>
    );
}

export default MainRoute;