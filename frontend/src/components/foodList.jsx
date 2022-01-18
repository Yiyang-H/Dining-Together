import * as React from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import FoodCard from "./foodCard";
import Container from '@mui/material/Container';

const containerStyle = {
    paddingTop:"200px"
}

export default function BasicGrid() {
  return (
    <Container maxWidth="md" style={containerStyle}>

    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={1}>
        <Grid item xs={3}>
            <FoodCard/>
        </Grid>
        <Grid item xs={3}>
          <FoodCard/>
        </Grid>
        <Grid item xs={3}>
          <FoodCard/>
        </Grid>
        <Grid item xs={3}>
          <FoodCard/>
        </Grid>

        <Grid item xs={3}>
            <FoodCard/>
        </Grid>
        <Grid item xs={3}>
          <FoodCard/>
        </Grid>
        <Grid item xs={3}>
          <FoodCard/>
        </Grid>
        <Grid item xs={3}>
          <FoodCard/>
        </Grid>

      </Grid>
    </Box>
    </Container>
  );
}