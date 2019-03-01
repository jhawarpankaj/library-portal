var Home = React.createClass({
  render() {
	  
	  var imgUrl = 'public/Library.jpg';
	  const TodoComponent = {
			  width: "300px",
			  margin: "30px auto",
			  backgroundColor: "#fffff",
			  minHeight: "200px",
			  boxSizing: "border-box",
			};
	  
    return (
		<div style={TodoComponent}>
		
            <h4>Welcome to Library!</h4>
            <ul className="header">
              <li><a href="/search">Search</a></li>
              <li><a href="/checkin">CheckIn</a></li>
              <li><a href="/borrower">Create Borrower</a></li>
              <li><a href="/fines">Calculate Fines</a></li>
            </ul>
            <div className="content">
            </div>
        </div>
    );
  }
});

ReactDOM.render(
  <Home/>, 
  document.getElementById("root")
);