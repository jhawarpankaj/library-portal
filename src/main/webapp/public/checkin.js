var App = React.createClass({

  	loadBooksFromServer: function() {
		var self = this;
		const uri = "http://localhost:8080/api/book/checkin/search/" + this.state.value;
		fetch(uri)
		.then((resp) => resp.json())
		.then(function(data) {
		    self.setState({books: data});
		  });
  },

	getInitialState: function() {
		return { 
			books: [], 
			flag:false 
		};
	},
  
	handleChange() {
	  this.state.flag = true;
	  this.loadBooksFromServer();
	},
  
	updateInputValue(e) {
	  this.setState({value: e.target.value});
	  console.log(this.state.value)
	},

	render: function() {
		const textStyle = {
		  color: 'blue',
			width: '500px',		 
		    border: '1px solid #fffff',
		};
	  if (this.state.flag === true){
		  return(
				  <div><BooksTable books={this.state.books}/></div>
		  );
	  }
	  else{
		  return(
				  <div>
				  	<div><a href='http://localhost:8080/'>Home</a></div><br/>				 
				  	<div>	 
			    		<input type="text" style={textStyle} value={this.state.value} onChange={this.updateInputValue} placeholder="Enter card no or isbn..."/>
			            <button className="button is-info" onClick={this.handleChange.bind(this)}>Search</button>
		            </div>
	            </div>
		  );
	  }
}});

var BooksTable = React.createClass({

	render: function() {
		var merged = [].concat.apply([], this.props.books);
	    var rows = [];
	    console.log("Merged length: ", merged)
	    if(merged.length == 0){
	    	alert("Nothing to check in!");
	    	location.reload();
	    	return;
	    }
	    merged.forEach(function(book) {
	      rows.push(
	        <Book book={book}/>);
	    });

    return (
      <table className="table table-striped">
          <thead>
              <tr>
                  <th>CARDNO</th>
                  <th>ISBN</th>
                  <th>SSN</th>
                  <th>BORROWER NAME</th>
                  <th>CHECK IN</th>
              </tr>
          </thead>
          <tbody>{rows}</tbody>
      </table>
    );
  }
});

var Book = React.createClass({

	getInitialState: function() {
	    return {
	    	display: true,
	    	showPopup:false,
	    	cardId: "",
	    	isEligible: {},
	    	isbn: "",
	    	checkoutStatusAndMessage: {},
	    	alert:null
	    	};
	  },
	  
	  handleClick: function(e) {
		  var self = this;
		  var message = "";
			const uri = "http://localhost:8080/api/book/checkin/check/" + this.props.book.cardno.trim() + " " + this.props.book.isbn.trim();
			fetch(uri)
			.then((resp) => resp.json())
			.then(function(data) {
				alert(data['message']);
				self.setState({status: data['status']});
				location.reload();
			  });
			console.log(this.state.status);
		    },
		    
	updateInputValue(e) {
  	  this.setState({cardId: this.props.book.cardno});
  	  this.setState({isbn: this.props.book.isbn});
  	  console.log(this.state.isbn)
  	},
  	
  	hideAlert() {
  	    console.log('Hiding alert...');
  	    this.setState({
  	      alert: null
  	    });
  	  },

	render: function() {
	    if (this.state.display==false) 
	    	return null;
	    if (this.props.book.date_in != null)
	    	return null;
	    else 
	    	return (
		      	<tr>
		      		<td>{this.props.book.cardno}</td>
	          		<td>{this.props.book.isbn}</td>
	          		<td>{this.props.book.ssn}</td>
	          		<td>{this.props.book.borrower_name}</td>
	          		<td><input type="button" value="CheckIn" onClick={this.handleClick}/></td>
	      		</tr>
    		);
  	}
});

ReactDOM.render(<App/>, document.getElementById('checkin'));
