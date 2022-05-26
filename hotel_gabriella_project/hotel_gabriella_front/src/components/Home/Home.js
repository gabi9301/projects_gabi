import { useState } from "react";
import classes from "./Home.module.css";
import ResvOption from "./ResvOption/ResvOption";
import SearchRoom from "./Search/SearchRoom";
import Ocean from "../../assets/image/Ocean.jpg";
import DateProvider from "../../store/DateProvider";
import SearchProvider from "../../store/SearchProvider";
const Home = (props) => {
  const [resvOptionShown, setResvOptionShown] = useState(false);

  const showResvOptionHandler = () => {
    setResvOptionShown(true);
  };

  const hideResvOptionHandler = () => {
    setResvOptionShown(false);
  };

  return (
    <main className={classes.container}>
      <DateProvider>
        <SearchProvider>
          <SearchRoom onShowResvOption={showResvOptionHandler} />
          {resvOptionShown && <ResvOption onClose={hideResvOptionHandler} />}
        </SearchProvider>
      </DateProvider>
      <img src={Ocean} alt="ocean" className={classes["main-image"]} />
    </main>
  );
};

export default Home;
