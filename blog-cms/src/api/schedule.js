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