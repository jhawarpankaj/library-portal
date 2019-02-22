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

    if (this.state.display==false) return null;
    else return (
      <tr>
          <td>{this.props.book.title}</td>
          <td>
            <button className="btn btn-info" onClick={this.handleDelete}>Delete</button>
          </td>
      </tr>
    );
  }
});









var BooksTable = React.createClass({

  render: function() {

    var rows = [];
    this.props.books.forEach(function(book) {
      rows.push(
        <Book book={book} key={book.title} />);
    });

    return (
      <table className="table table-striped">
          <thead>
              <tr>
                  <th>Book Title</th>
                  <th>Delete</th>
              </tr>
          </thead>
          <tbody>{rows}</tbody>
      </table>
    );
  }
});








var App = React.createClass({

  loadBooksFromServer: function() {

    var self = this;
    $.ajax({
        url: "http://localhost:8080/api/books",
      }).then(function(data) {
        self.setState({ books: data._embedded.books });
      });

  },

  getInitialState: function() {
    return { books: [] };
  },

  componentDidMount: function() {
    this.loadBooksFromServer();
  },

  render() {
    return ( <BooksTable books={this.state.books} /> );
  }
});

ReactDOM.render(<App />, document.getElementById('root') );