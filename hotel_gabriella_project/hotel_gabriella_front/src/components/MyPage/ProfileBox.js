import { useEffect } from "react";
import classes from "./ProfileBox.module.css";

const ProfileBox = (props) => {

    const memberInfo = props.mypageInfo.memberInfo;

return (
    
    <div className={classes.profile_container}>

        <div className={classes.profile_photo}>

        </div>
        <span>{memberInfo.account}</span>
    </div>
    );
}

export default ProfileBox;