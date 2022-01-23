import Box from '@mui/material/Box';

export default function IMmgHolder(props){
    return(
        <Box style={{
            width: 150, height:150, background: '#D8CBF6',
            borderRadius: '25px 25px 40px 40px'
        }}>
            <Box sx={{width: '97%', height: '97%', background: '#FDFAE5', overflow: 'hidden', display: 'flex', alignItems: 'flex-end', justifyContent: 'flex-end', 
            borderRadius: '40px 25px 40px 40px'}}>
            <img style={{objectFit: 'cover', width: '97%', height: '97%', borderRadius: '40px 25px 40px 40px'}} src={props.src}></img>
            </Box>
        </Box>
    )
}

