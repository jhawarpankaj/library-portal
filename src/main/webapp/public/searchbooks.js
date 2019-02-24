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
	    var rows = [];
	    this.props.books.forEach(function(book) {
	      rows.push(
	        <Book book={book}/>);
	    });

    return (
      <table className="table table-striped">
          <thead>
              <tr>
                  <th>ISBN</th>
                  <th>BOOK TITLE</th>
              </tr>
          </thead>
          <tbody>{rows}</tbody>
      </table>
    );
  }
});

var Book = React.createClass({

	getInitialState: function() {
	    return {display: true };
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

	render: function() {
	    if (this.state.display==false) 
	    	return null;
	    else 
	    	return (
		      	<tr>
		      		<td>{this.props.book.isbn}</td>
	          		<td>{this.props.book.title}</td>
	          		<td><button className="btn btn-info" onClick={this.handleDelete}>Delete</button></td>
		      	</tr>
    		);
  	}
});

ReactDOM.render(<App/>, document.getElementById('search'));
