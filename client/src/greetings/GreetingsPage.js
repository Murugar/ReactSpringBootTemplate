import React from 'react'
import {connect} from 'react-redux'
import {Button, FormControl, FormGroup, ControlLabel, Row, Col} from 'react-bootstrap'

import {getGreetings, getWelcome} from './greetingsReducer'

import './GreetingsPage.css'

export class GreetingsPage extends React.Component {
  constructor() {
    super()

    this.state = {
      namedGreetingsParameter: ''
    }
  }

  handleNameChange(event) {
    this.setState({ namedGreetingsParameter: event.target.value })
  }

  render() {
    return (
    
    		  <div className="row">	
    	  		<div className="col-md-offset-1 col-md-11">
    	  		<div className="panel panel-primary">
    	  		  <div className="panel-heading">REST => Spring Boot</div>
    	  		  <div className="panel-body">	
    		
      <div className="Greetings">
        <h2 className="text-success">Make Rest Calls</h2>
        <Row>
          <Col sm={4}>
            <br/>
            <ControlLabel className="text-primary">GET /rest/greetings</ControlLabel>
            <br/>
            <br/>
            <Button bsStyle="success" onClick={() => this.props.getGreetings()}>Perform Request</Button>
          </Col>
          <Col sm={8}>
            <FormGroup>
              <ControlLabel className="text-danger">Server Response</ControlLabel>
              <FormControl componentClass="textarea" value={this.props.simpleGreetingsResponse} />
            </FormGroup>
          </Col>
        </Row>
        <Row>
        <Col sm={4}>
          <br/>
          <ControlLabel className="text-warning">GET /rest/welcome</ControlLabel>
          <br/>
          <br/>
          <Button bsStyle="warning" onClick={() => this.props.getWelcome()}>Perform Welcome request</Button>
       
        </Col>
        <Col sm={8}>
          <FormGroup >
            <ControlLabel className="text-danger">Server Response</ControlLabel>
            <FormControl componentClass="textarea" 
            	value={this.props.welcomeResponse} /> 
          </FormGroup>
        </Col>
      </Row>
        <Row>
          <Col sm={4}>
            <ControlLabel className="text-primary" >GET /rest/greetings/{'{name}'}</ControlLabel>
            <FormControl type="text" placeholder="{name}" value={this.state.namedGreetingsParameter} onChange={this.handleNameChange.bind(this)} />
            <br/>
            <Button
              bsStyle="danger"
              onClick={() => this.props.getGreetings(this.state.namedGreetingsParameter)}
              disabled={!this.state.namedGreetingsParameter}
            >
              Perform Request
            </Button>
          </Col>
          <Col sm={8}>
            <FormGroup>
              <ControlLabel className="text-danger">Server Response</ControlLabel>
              <FormControl componentClass="textarea" value={this.props.namedGreetingsResponse} />
            </FormGroup>
          </Col>
        </Row>
      </div>
      </div>
      </div>
      </div>
      </div>
    )
  }
}

function mapStateToProps(state) {
  return {
    simpleGreetingsResponse: state.greetings.simpleGreetingsResponse,
    namedGreetingsResponse: state.greetings.namedGreetingsResponse,
    welcomeResponse: state.greetings.welcomeResponse
  }
}

function mapDispatchToProps(dispatch) {
  return {
    getGreetings: (param) => dispatch(getGreetings(param)),
    getWelcome: () => dispatch(getWelcome())
  }
}



export default connect(mapStateToProps, mapDispatchToProps)(GreetingsPage)
