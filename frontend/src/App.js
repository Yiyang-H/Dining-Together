import logo from './logo.svg';
import './App.css';

import Login from './pages/Login';
import { createTheme } from '@mui/material/styles';
import {ThemeProvider} from '@mui/material/styles'

const theme = createTheme({
    palette: {
        primary: {
            main: '#f7c531',
        },
        secondary: {
            main: '#f50057',
        },
    },
});

function App() {
  return (
      <ThemeProvider theme={theme}>
          <Login/>
      </ThemeProvider>
    
  );
}

export default App;
