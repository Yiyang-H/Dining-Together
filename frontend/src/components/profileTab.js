import * as React from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import { createTheme, ThemeProvider } from '@mui/material/styles';


export default function ProfileTab(){
    
    const outerTheme = createTheme({
        palette: {
          primary: {
            main: "#F7C531",
          },
        },
      });
    const [value, setValue] = React.useState('one');

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    return(
        <ThemeProvider theme={outerTheme}>
        <Container>
            <Box sx={{ width: '100%' }}>
            <Tabs
                value={value}
                onChange={handleChange}
                textColor="primary"
                // aria-label="secondary tabs example"
                centered
                TabIndicatorProps={{
                    style: {
                      backgroundColor: "#F7C531"
                     }
                }}
            >
                <Tab value="one" label="Orders" />
                <Tab value="two" label="My food" />
            </Tabs>
            </Box>
        </Container>
        </ThemeProvider>

    )
}