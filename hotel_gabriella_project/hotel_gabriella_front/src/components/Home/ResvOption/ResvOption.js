import Modal from "../../UI/Modal";
import ResvDetail from "./ResvDetail";
import classes from "./ResvOption.module.css";

const ResvOption = (props) => {
  return (
    <Modal onClose={props.onClose}>
      <ResvDetail />
    </Modal>
  );
};

export default ResvOption;
