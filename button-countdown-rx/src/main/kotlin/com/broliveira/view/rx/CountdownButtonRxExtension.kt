package com.broliveira.view.rx

import com.broliveira.view.CountdownButton
import io.reactivex.Observable

fun CountdownButton.countdown(): Observable<Unit> {
  return Observable.create { emitter ->
    this.startCountDown { isFinished ->
      if (isFinished)
        emitter.onNext(Unit)
      emitter.onComplete()
    }
  }
}
