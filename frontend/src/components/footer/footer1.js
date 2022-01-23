import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';

export default function Footer1(){
    const footerHeader={
        fontFamily: 'Quicksand',
        fontStyle: 'bold', 
        fontWeight: '500',
        fontSize: '22px',
    }
    const footerBody={
        fontFamily: 'Quicksand',
        fontStyle: 'normal', 
        fontWeight: '400',
        fontSize: '16px',
        color:"#8B8BA5",
        padding:"5px"
    }
    return(<footer>
        <Box sx={{display:"flex", flexDirection:"row",justifyContent:"center",alignItems:"center",margin:"60px 0 0 0 " ,borderTop:"1px solid #ECECEC"}}>
        <Typography gutterBottom variant="h4" component="div" 
            style={{fontFamily: 'Quicksand',
                    fontStyle: 'bold', 
                    fontWeight: '500',
                    fontSize: '28px',
                    padding:"20px"
                    }}>
            🍔 Dining Together
            </Typography>
          
        </Box>
        <Box sx={{display:"flex", flexDirection:"column",justifyContent:"center",alignItems:"center"}}>
            <Typography gutterBottom variant="h4" component="div" 
            style={footerHeader}>
                Be Our Friend
            </Typography>
            <Typography gutterBottom variant="h4" component="div" 
            style={footerBody}>
                123 abc Street, Melbourne
            </Typography>
            <Typography gutterBottom variant="h4" component="div" 
            style={footerBody}>
                support@diningtogether.com
            </Typography>
            <Typography gutterBottom variant="h4" component="div" 
            style={footerBody}>
                021 - 1111 - 2222
            </Typography>
            <Typography gutterBottom variant="h4" component="div" 
            style={footerBody}>
               All Rights Reserved Foodyar by Pixel from BuildWith Angga 2020
            </Typography>
        </Box>
    </footer>)
}