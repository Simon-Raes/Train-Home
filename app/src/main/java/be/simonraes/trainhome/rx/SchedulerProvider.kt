package be.simonraes.trainhome.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun ui(): Scheduler


    fun computation(): Scheduler


    fun trampoline(): Scheduler


    fun newThread(): Scheduler


    fun io(): Scheduler

}
