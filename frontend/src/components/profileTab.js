import * as React from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import OrderCard from './orderCard';
import TabPanel from '@mui/lab/TabPanel';
import TabContext from '@mui/lab/TabContext';
import TabList from '@mui/lab/TabList';
import MyFoodCard from './myFoodCard/myFood';
import { fetchUsersOrder } from '../api/getUsersOrder';
import {useEffect, useState} from 'react';


export default function ProfileTab(props){
    
    const outerTheme = createTheme({
        palette: {
          primary: {
            main: "#F7C531",
          },
        },
    });

    const coloumStyle={
        overflowY: 'scroll'
    }
    const [value, setValue] = React.useState('1');

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const [orderDatail,setOrderDetails]=useState([])

    useEffect(() => {
        async function fetchUserOrderFunction() {
            const orderData = await fetchUsersOrder();
            if(orderData) {
                setOrderDetails(orderData);
            }
        }
        fetchUserOrderFunction();
    }, [])

    return(
        <ThemeProvider theme={outerTheme}>
        <Container style={coloumStyle}>
            <Box sx={{ width: '100%' }}>
            <TabContext value={value}>
                <TabList onChange={handleChange} aria-label="lab API tabs example" centered>
                <Tab value="1" label="Orders" />
                <Tab value="2" label="My food" />
                </TabList>
                <TabPanel value="1" sx={{padding:0,margin:0}}>
                {orderDatail && orderDatail.map((applyItem) => (
                    // <Grid item xs={3}>
                        <OrderCard foodItem={applyItem} />
                    // </Grid>
                 ))}
                </TabPanel>
                <TabPanel value="2"><MyFoodCard/></TabPanel> 

            </TabContext>
            </Box>
        </Container>
        </ThemeProvider>

    )
}