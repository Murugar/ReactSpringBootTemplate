import React from 'react'

import {Nav, NavItem} from 'react-bootstrap'
import {IndexLinkContainer,LinkContainer} from 'react-router-bootstrap'

import logo from './logo.svg'
import './App.css'

const App = (props) => {
  return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="text-primary">Welcome to React Spring Boot</h1>
        </div>
        <div className="App-menu text-info">
          <Nav bsStyle="tabs" className="text-primary">
            <IndexLinkContainer to="/"><NavItem><span className="text-primary">Home</span></NavItem></IndexLinkContainer>
            <LinkContainer to="/greetings"><NavItem><span className="text-primary">REST(Driven By Spring Boot)</span></NavItem></LinkContainer>
            <LinkContainer to="/login"><NavItem><span className="text-primary">Login</span></NavItem></LinkContainer>
          </Nav>
        </div>
        <div className="App-content test-primary">
          {props.children}
        </div>
      </div>
  );
}

export default App;
