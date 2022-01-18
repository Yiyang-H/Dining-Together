import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';
import Rating from '@mui/material/Rating';

export default function FoodCard() {
  const [value, setValue] = React.useState(2);

  const ratingStyle={
    width: '100%',
    height: '30%',
    justifyContent: 'center',
    alignItems: 'center'
  }

  

  return (
    <Card sx={{ maxWidth: 200 }} >
      <CardActionArea justifyContent='center'>
        <CardMedia
          component="img"
          height="140"
          image="https://picsum.photos/seed/picsum/200/300"
          alt="green iguana"
        />
        <CardContent>
          <Typography gutterBottom variant="h7" component="div" textAlign="center">
          Naga Fruity Joss
          </Typography>
          <Typography variant="body2" color="text.secondary" textAlign="center">
          Imelda Saranghae

            {/* <Rating name="read-only" justifyContent='center'  value={value} readOnly /> */}
          </Typography>
          <Rating name="read-only" value={value} readOnly style={ratingStyle}/>
        </CardContent>
      </CardActionArea>
    </Card>
  );
}