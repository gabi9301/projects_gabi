
import Modal from "../UI/Modal";
import BookDetail from "./BookDetail";

const ReserveBox = (props) =>{
return(

    <Modal onClose={props.onClose}>
            <BookDetail searchData={props.searchData} roomId={props.roomId} viewType={props.viewType} roomType={props.roomType}/>
    </Modal>

)



}

export default ReserveBox;

