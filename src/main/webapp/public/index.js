var Home = React.createClass({
  render() {
    return (
		<div>
            <h1>Welcome to Library!</h1>
            <ul className="header">
              <li><a href="/search">Search</a></li>
              <li><a href="/checkin">CheckIN</a></li>
              <li><a href="/borrower">Create Borrower</a></li>
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