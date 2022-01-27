import React from 'react';
import base64 from 'react-native-base64'
export class Base64Decoder extends React.Component {
    constructor(props){
        alert();
        super(props);
        this.state = {text: null}
        
    }
    componentDidMount() {
        
        this.decodeString(this.props.base64);
    }
    decodeString(encText){
        this.state.text = base64.decode(encText);
       
    }
   render() {
    
      return (
        
        <p>{this.state.text}</p>
      );
   }
}

