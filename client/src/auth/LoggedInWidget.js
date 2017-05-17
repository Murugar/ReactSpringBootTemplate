import React from 'react'
import {Button} from 'react-bootstrap'

export default function(props) {
  return (
		  <div className="row">	
  		<div className="col-md-offset-3 col-md-6">
  		<div className="panel panel-primary">
  		  <div className="panel-heading">Welcome</div>
  		  <div className="panel-body">			  
    <div className="LoggedInWidget">
      <br/>
      <p className="text-primary"><h3>Logged in as ‟{props.username}” !</h3></p>
      <br/>
      <Button bsStyle="danger" onClick={props.onLogout}>
        Log out
      </Button>
    </div>
    </div>
    </div>
    </div>
    </div>
  )
}
