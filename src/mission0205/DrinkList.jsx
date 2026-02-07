function Drink({ name }) {

    let part, caffeine, age;

    if (name === '녹차') {
      part = '녹차잎';
      caffeine = '15-70 mg/cup';
      age = '4000념 전';
    } else {
      part ='키피콩';
      caffeine = '80-85 mg/cup';
      age = '1000년';
    }

  return (
    <section>
      <h1>{name}</h1>
      <dl>
        <dt>원료</dt>
        <dd>{part}</dd>
        <dt>카페인</dt>
        <dd>{caffeine}</dd>
        <dt>역사</dt>
        <dd>{age}</dd>
      </dl>
    </section>
  );
}

export default function DrinkList() {
  return (
    <div>
      <Drink name="녹차" />
      <Drink name="커피" />
    </div>  
  );
}