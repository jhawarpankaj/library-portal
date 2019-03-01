var App = React.createClass({

  	loadBooksFromServer: function() {
		var self = this;
		const uri = "http://localhost:8080/api/book/search/" + this.state.value;
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
		
		console.log("Length of array: " , this.state.books.length);
		
	const textStyle = {
			  color: 'blue',
				width: '500px',	 
			    border: '1px solid #fffff',
			};
	
	  if (this.state.flag === true){
		  return(
				  <div>
					  <a href='http://localhost:8080/'>Home</a>
					  <BooksTable books={this.state.books}/>
				  </div>
		  );
	  }
	  else{
		  return(
				 <div>
				 	<div><a href='http://localhost:8080/'>Home</a></div><br/>
				 	<div>
			    		<input type="text" value={this.state.value} style={textStyle} onChange={this.updateInputValue} placeholder="Type something..."/>
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
	    if(merged.length == 0){
	    	alert("No such book found!");
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
                  <th>ISBN</th>
                  <th>BOOK TITLE</th>
                  <th>AUTHOR NAME</th>
                  <th>ISAVAILABLE</th>
                  <th>CHECKOUT</th>
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
		  var message = ""
			const uri = "http://localhost:8080/api/checkout/" + this.state.cardId.trim() + " " + this.state.isbn;
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
  	  this.setState({cardId: e.target.value});
  	  this.setState({isbn: this.props.book.isbn});
  	  console.log(this.state.isbn)
  	},

	render: function() {
	    if (this.state.display==false) 
	    	return null;
	    else 
	    	return (
		      	<tr>
		      		<td>{this.props.book.isbn}</td>
	          		<td>{this.props.book.title}</td>
	          		<td>{this.props.book.author_name}</td>
	          		<td>{this.props.book.isavailable}</td>
	          		{this.props.book.isavailable === 'YES'?
	          				<td>
				          		<div>
				          			<input type="text" onChange={this.updateInputValue} placeholder="Enter Card No..."/>
				          			<input type="button" value="Check" onClick={this.handleClick}/>
			                	</div>
			               </td>
			               
	          					: 'NA'
	          		}
		      	</tr>
    		);
  	}
});

ReactDOM.render(<App/>, document.getElementById('search'));
