import Profile from './Profile.jsx';

export default function Gallery() {
  return (
    <div>
      <h1>Notable Scientists</h1>
      <Profile person={{
        name: 'Maria Skłodowska-Curie',
        imageId: 'szV5sdG',
        profession: 'physicist and chemist',
        awards: ['Nobel Prize in Physics', 'Nobel Prize in Chemistry','Davy Medal', 'Matteucci Medal'],
        discovered: 'polonium (chemicla element)',
      }} />
      <Profile person={{
        name: 'Katsuko Saruhashi',
        imageId: 'YfeOqp2',
        profession: 'geochemist',
        awards: ['Miyake Prize for geochemistry', 'Tanaka Prize'],
        discovered: 'a method for measuricarbon dioxide levels in seawater'
      }} />
    </div>
  );  
}