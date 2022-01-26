import { Container, Modal } from "@mui/material";
import ProvideFood from "../pages/ProvideFood/provideFood";

export default function ProvideFoodModal(props) {
    const {open, handleClose} = props;

    const modalStyle = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        bgcolor: 'background.paper',
        border: '2px solid #000',
        p: 4,
        maxHeight: '90vh',
        maxWidth: '90vw'
    };

    const textOverflowStyle={
        overflowY: 'scroll'
    }

    return (
        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="parent-modal-title"
            aria-describedby="parent-modal-description"
        >
            <Container sx={modalStyle} style={textOverflowStyle}>
            <ProvideFood handleClose={handleClose}/>
            </Container>
        </Modal>
    )
}