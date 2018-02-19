import React, {Component} from 'react'
import ReactDOM from 'react-dom'
import {Grid, Nav, Navbar, NavItem} from 'react-bootstrap'
import {Form} from 'react-bootstrap-form'
import {BrowserRouter, Route, Switch} from 'react-router-dom'
import Home from "./component/admin/Home.jsx"
import Authorization from "./component/authorization/Authorization.jsx"
import Registration from "./component/authorization/Registration.jsx"
import Login from "./component/authorization/Login.jsx"
import ProjectList from "./component/admin/ProjectList.jsx"
import Task from "./component/admin/Task.jsx"


class App extends React.Component {
    constructor(props) {
        super(props)
        this.state = {user: 'undefined'};
        this.setUser = this.setUser.bind(this);
    }


    componentDidMount() {
        this.setUser();
    }

    setUser() {
        var self = this
        fetch('http://localhost:8080/user', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Cache': 'no-cache'
            },
            credentials: 'same-origin'
        })
            .then(
                function (response) {
                    response.json().then(function (data) {
                        self.setState({user: data});

                    })

                }
            )
    }

    render() {
        var user = this.state.user

        const WrappedHome = function (props) {
            return (<Home{...props} user={user}/>)

        }

        const WrappedProjectList = function (props) {
            return (<ProjectList{...props} role={user.role}/>)
        }

        const WrappedTask = function (props) {
            return (<Task{...props} role={user.role}/>)

        }

        if (this.state.user != 'undefined') {
            return (<BrowserRouter>
                <div>
                    <Route path='/' component={WrappedHome}/>
                    <Switch>
                        <Route exact path='/' component={Home}/>
                        <Route path='/project' component={WrappedProjectList}/>
                        <Route path='/task/:id?' component={WrappedTask}/>
                    </Switch>
                </div>
            </BrowserRouter>);
        } else {
            return (
                <BrowserRouter>
                    <div>
                        <Route path='/' component={Authorization}/>
                        <Switch>
                             <Route path="/login" component={Login}/>
                            <Route path='/registration' component={Registration}/>
                        </Switch>
                    </div>
                </BrowserRouter>
            )
        }

    }
}

ReactDOM.render(<App/>, document.getElementById('index'))