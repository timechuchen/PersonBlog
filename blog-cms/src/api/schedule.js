import axios from '@/utils/request'

export function updateVisitLogToMySql() {
    return axios({
        url: '/syncVisitInfoToDatabase',
        method: 'PUT',
    })
}

export function delVisitInfoToDatabase() {
    return axios({
        url: '/delVisitInfoToDatabase',
        method: 'DELETE',
    })
}

export function emptyExceptionLog() {
    return axios({
        url: '/emptyExceptionLog',
        method: 'DELETE',
    })
}

export function updateLikesOfDB() {
    return axios({
        url: '/updateLikesOfDB',
        method: 'PUT',
    })
}