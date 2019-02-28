var App = React.createClass({

  	calculateFines: function() {
		var self = this;
		const uri = "http://localhost:8080/api/fines/calculate";
		fetch(uri)
		.then((resp) => resp.json())
		.then(function(data) {
			alert(data['message']);
		  });
	},
	
	displayFines: function() {
		var self = this;
		const uri = "http://localhost:8080/api/fines/display";
		fetch(uri)
		.then((resp) => resp.json())
		.then(function(data) {
			alert(data['message']);
			self.setState({fines: data});
		  });
	},

	getInitialState: function() {
		return { 
			fines: [],
			flag : false
		};
	},
	
	handleChange(event) {
		this.calculateFines();
	  },
	  
	  handleChange2(event) {
		  this.state.flag = true;
		  this.displayFines();
	  },

	render: function() {
		if (this.state.flag === true){
			  return(
					  <div><FinesTable fines={this.state.fines}/></div>
			  );
		  }
		else{
		  return(
				  <div>
					  <button className="button is-info" onClick={this.handleChange.bind(this)}>Refresh Fines</button>
					  <button className="button is-info" onClick={this.handleChange2.bind(this)}>Pay Fines</button>
				  </div>
		  );
	  }
}});

var FinesTable = React.createClass({

	render: function() {
		var merged = [].concat.apply([], this.props.fines);
	    var rows = [];
	    merged.forEach(function(fine) {
	      rows.push(
	        <Fine fine={fine}/>) ;
	    });

    return (
      <table className="table table-striped">
          <thead>
              <tr>
                  <th>LOAN IDS</th>
                  <th>CARD ID</th>
                  <th>LOAN AMOUNT</th>
                  <th>PAY ALL FINES</th>
              </tr>
          </thead>
          <tbody>{rows}</tbody>
      </table>
    );
  }
});
	    
	    
var Fine = React.createClass({

	getInitialState: function() {
	    return {
	    	};
	  },
	  
	handleClick: function(e) {
		  var self = this;
			const uri = "http://localhost:8080/api/fines/pay/" + this.props.fine.card_id.trim();
			console.log(uri);
			fetch(uri)
			.then((resp) => resp.json())
			.then(function(data) {
				console.log("Return of the api call.");
				console.log('data:', data);
				console.log('data message:', data['message']);
				console.log('type of data:', (typeof data));
				alert(data['message']);
			  });
		    },

	render: function() {
	    if (this.state.display==false) 
	    	return null;
	    else 
	    	return (
		      	<tr>
		      		<td>{this.props.fine.loan_ids}</td>
	          		<td>{this.props.fine.card_id}</td>
	          		<td>{this.props.fine.total_fine}</td>
	          				<td>
				          		<div>
				          			<input type="button" value="Pay Due" onClick={this.handleClick}/>
			                	</div>
			               </td>
		      	</tr>
    		);
  	}
});

ReactDOM.render(<App/>, document.getElementById('fines'));
