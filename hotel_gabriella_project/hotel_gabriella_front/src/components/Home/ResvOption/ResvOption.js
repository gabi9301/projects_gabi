import Modal from "../../UI/Modal";
import ResvDetail from "./ResvDetail";
import classes from "./ResvOption.module.css";

const ResvOption = (props) => {
  return (
    <Modal onClose={props.onClose}>
      <ResvDetail onClose={props.onClose}/>
    </Modal>
  );
};

export default ResvOption;
