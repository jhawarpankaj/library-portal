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
	          		<td>{this.props.book.author_name}</td>
	          		<td>{this.props.book.isavailable}</td>
	          		<td><button className="btn btn-info" onClick={this.handleDelete}>Delete</button></td>
		      	</tr>
    		);
  	}
});