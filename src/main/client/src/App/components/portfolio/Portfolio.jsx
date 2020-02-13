import React from "react";
import './Portfolio.css';
import { PortfolioPiece } from '../portfolioPiece/PortfolioPiece'

export class Portfolio extends React.Component {
  getGridNumber(){
    var count = React.Children.count(this.props.children); 
    if(count > 4){
      return 3;
    }
    else{
      return (12/count);
    }
  }
  render() {
    return (
      <div className="portfolio">
        {React.Children.map(this.props.children, (child,i) => {
          if(child.type === PortfolioPiece){
            return (<div className={"grid-" + this.getGridNumber()}>{child}</div>);
          }
        })}
        
      </div>
    );
  }
}
