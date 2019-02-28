var App = React.createClass({

  	loadBooksFromServer: function() {
		var self = this;
		const uri = "http://localhost:8080/api/book/search/" + this.state.value;
		fetch(uri)
		.then((resp) => resp.json())
		.then(function(data) {
			console.log("Return of the api call.");
			console.log('data:', data);
			console.log('type of data:', (typeof data));
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
	  if (this.state.flag === true){
		  return(
				  <div><BooksTable books={this.state.books}/></div>
		  );
	  }
	  else{
		  return(
				 <div>	 
		    		<input type="text" value={this.state.value} onChange={this.updateInputValue} placeholder="Something that needs to be done..."/>
		            <button className="button is-info" onClick={this.handleChange.bind(this)}>Search</button>
	             </div>
		  );
	  }
}});

var BooksTable = React.createClass({

	render: function() {
		var merged = [].concat.apply([], this.props.books);
	    var rows = [];
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
	  
	  togglePopup() {
		    this.setState({
		      showPopup: !this.state.showPopup
		    });
		  },

	handleDelete() {
	    var self = this;
	    $.ajax({
	        url: self.props.Book._links.self.href,
	        type: 'DELETE',
	        success: function(result) {
	          self.setState({display: false});
	        },
	        error: function(xhr, ajaxOptions, thrownError) {
	          toastr.error(xhr.responseJSON.message);
	        }
	    });
	  },
	  
	  handleClick: function(e) {
		  var self = this;
		  var message = ""
			const uri = "http://localhost:8080/api/checkout/" + this.state.cardId.trim() + " " + this.state.isbn;
			fetch(uri)
			.then((resp) => resp.json())
			.then(function(data) {
				console.log("Return of the api call.");
				console.log('data:', data);
				console.log('data message:', data['message']);
				console.log('type of data:', (typeof data));
				message = data['message'];
				alert(data['message']);
				self.setState({status: data['status']});
			  });
			console.log(this.state.status);
		    },
		    
	updateInputValue(e) {
  	  this.setState({cardId: e.target.value});
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
