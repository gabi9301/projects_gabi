import Home from "./components/Home/Home";
import Header from "./components/Layout/Header";
import { BrowserRouter } from "react-router-dom";
import MainRoute from "./components/MainRoutes";
import Footer from "./components/Layout/Footer";

function App() {
  return (
    <BrowserRouter>
      <Header />
      <MainRoute>
        <Home />
      </MainRoute>
      <Footer />
    </BrowserRouter>
  );
}
export default App;
