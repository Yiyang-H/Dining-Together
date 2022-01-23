import * as React from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import FoodCard from "./foodCard";
import Container from '@mui/material/Container';

export default function FoodList(props) {

  return (
    <Container maxWidth="md">

    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={1}>
      
      {props.foodlist && props.foodlist.map((food) => (
          <Grid item xs={3}>
            <FoodCard foodItem={food} />
          </Grid>
        ))}

      </Grid>
    </Box>
    </Container>
  );
}