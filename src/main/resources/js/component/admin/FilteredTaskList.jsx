import React from 'react'
import NewTask from "./NewTask.jsx"
import {Link} from "react-router-dom";

export default class FilteredTaskList extends React.Component {
    constructor(props) {
        super(props)
        this.state = {tasks: []}
        this.loadUsersTasks = this.loadUsersTasks.bind(this)
        this.onChange = this.onChange.bind(this)

    }


    loadUsersTasks() {
        var self = this
        fetch('http://localhost:8080/tasks', {
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

    onChange() {

        this.loadUsersTasks();
    }

    componentDidMount() {
        this.loadUsersTasks();
    }


    render() {
        const taskList = this.state.tasks.map(task =>
            <li key={task.id}><Link to={"/task/" + task.id}>{task.name}</Link></li>)
        return (
            <div class="container">
                <h3>Tasks</h3>
                <div class="col-sm-3" align="left">
                    <ul>
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