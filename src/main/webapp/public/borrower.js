var App = React.createClass({

  	verifyFormFieldsFromServer: function() {
		var self = this;
		if(this.state.inputvalueName == "" || this.state.inputvalueSSN == "" || this.state.inputvalueAddress == "" || this.state.inputvaluePhone == ""){
			alert('Empty field are not allowed');
			return;
		}
		const uri = "http://localhost:8080/api/borrower/create/" + this.state.inputvalueName + "||" + this.state.inputvalueSSN + "||" + this.state.inputvalueAddress + "||" + this.state.inputvaluePhone;
		fetch(uri)
		.then((resp) => resp.json())
		.then(function(data) {
			console.log("Return of the api call.");
			console.log('data:', data);
			console.log('type of data:', (typeof data));
			alert(data['message']);
		  });
	},

	getInitialState: function() {
		return { 
			books: [], 
			inputvalueName: "",
			inputvalueSSN: "",
			inputvalueAddress: "",
			inputvaluePhone: ""
		};
	},
	
	handleChangeName(event) {
	      this.setState({
	          inputvalueName: event.target.value
	      })
	  },
	  handleChangeSSN(event) {
	      this.setState({
	          inputvalueSSN: event.target.value
	      })
	  },
	  handleChangeAddress(event) {
	      this.setState({
	          inputvalueAddress: event.target.value
	      })
	  },
	  handleChangePhone(event) {
	      this.setState({
	          inputvaluePhone: event.target.value
	      })
	  },
	  handleSubmit (event) {
	      console.log('Form value: ' + this.state.inputvalueName);
	      this.verifyFormFieldsFromServer();
	      event.preventDefault();
	      this.refs.form.reset();
	      this.handleSubmit = this.handleSubmit.bind(this);
	  },
  
	updateInputValue(e) {
	  this.setState({value: e.target.value});
	  console.log(this.state.value)
	},

	render: function() {
		  return(
				  <div className="Borrower" style={{float: 'center', paddingRight : '5px'}}>
			          <form onSubmit={this.handleSubmit} ref="form">
			          <br/><br/>
			              <label>Enter Name:		</label>
			              <input type="text" value={this.state.inputvalueName} onChange={this.handleChangeName}/><br/>
			              <label>Enter SSN:			</label>
			              <input type="text" value={this.state.inputvalueSSN} onChange={this.handleChangeSSN}/><br/>
			              <label>Enter Address:		</label>
			              <input type="text" value={this.state.inputvalueAddress} onChange={this.handleChangeAddress}/><br/>
			              <label>Enter Phone:		</label>
			              <input type="text" value={this.state.inputvaluePhone} onChange={this.handleChangePhone}/><br/>			          
			              <input type="submit" value="Submit"/>
			          </form>
	              </div>
		  );
	  }
});

ReactDOM.render(<App/>, document.getElementById('borrower'));
