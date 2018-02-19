import React, {Component} from 'react'
import {Link} from "react-router-dom";
import NewTask from "./NewTask.jsx";

export default class TaskList extends React.Component {
    constructor(props) {
        super(props)
        this.state = {tasks: []}
        this.loadData = this.loadData.bind(this)
        this.onChange = this.onChange.bind(this)
    }


    componentDidMount() {

        this.loadData();
    }
    onChange() {

        this.loadData();
    }


    loadData() {
        var self = this
        fetch('http://localhost:8080/projects/'+ self.props.projectId +'/tasks', {
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
                        self.setState({tasks: data});

                    })

                }
            )
    }

    render() {
        const taskList = this.state.tasks.map(task =>
            <li key={task.id}><Link to={"/task/" +task.id }>{task.name}</Link></li>)

        return (<div class = "container">
                <h3>Tasks</h3>
                <div class = "col-sm-3" align="left">
                    <ul >
                        {taskList}
                    </ul>
                </div>
                <div class="col-sm-3" align="center">
                    <h3>New Task</h3>
                    <NewTask projectId={this.props.projectId} onChange={this.onChange}/>
                </div>
            </div>
        )
    }
}
